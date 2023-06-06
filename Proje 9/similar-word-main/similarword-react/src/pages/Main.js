import {useState} from "react";
import '../App.css'
import {Container, Row} from "reactstrap";
import contentService from "../service/ContentService";
import Navbar from "./common/Navbar";
import {Button, TextField} from "@mui/material";
import img from "../assets/png-transparent-computer-programming-programmer-computer-software-technical-support-programming-language-computer-computer-network-angle-furniture-removebg-preview.png";

function Main() {
    class ContentEntity {
        constructor(inputTexts, result, elapsedTime){
            this.inputTexts = inputTexts;
            this.result = result;
            this.elapsedTime = elapsedTime;

        }
    };
    const initialEntityState = new ContentEntity([],"",null);

    const [inputList, setInputList] = useState([{text : ''}]);
    const [submitted, setSubmitted] = useState(false);
    const [content, setContent] = useState(initialEntityState);
    var listOfInput;

    function handleinputchange(e, index) {
        const {name, value} = e.target;
        const list = [...inputList];
        list[index][name] = value;
        setInputList(list);
        }

    function handleaddclick() {
        setInputList([...inputList, {text : ''}]);
    }

    function handleremove(i) {
        const list=[...inputList];
        list.splice(i,1);
        setInputList(list);
    }

     async function saveTexts() {
         listOfInput = inputList.map(t => t.text);
        console.log(listOfInput);
        await contentService.generateFromMultipleString(listOfInput)
            .then(response =>{
               setContent(new ContentEntity(
                   response.data.inputTexts,
                   response.data.result,
                   response.data.elapsedTime))
                   console.log(response)
            })

         setSubmitted(true);
    }
    function tryAgain(){
        setSubmitted(false);
        setInputList([{text : ''}]);
    }
    function saveToDb(){
        contentService.saveContent(content);
        alert("Başarıyla kaydedildi.");
    }
    return(
       <div>
       <Navbar />
        <Container >

            {
                submitted ?
                    <div>
                        <div className={"inputs"}>
                            <h4>Inputs</h4>
                                {inputList.map( input => (
                                        <p> - {input.text}</p>
                                    )

                                )}
                        </div>
                        <div className={"content"}>
                             <h3 className={"header"}>{content.result}</h3>
                             <h5 className={"header"}>{content.elapsedTime} ns</h5>
                            <Button variant={'outlined'} onClick={tryAgain}>try again</Button>
                            <Button type={"submit"} variant={'outlined'} onClick={saveToDb}>Save to DB</Button>
                        </div>
                    </div> :

            <div>
                <div className={"inputs"}>
                    <img src={img} width={500} height={500}/>
                </div>
                <div className={"content"}>
                    {
                        inputList.map( (x,i)=>{
                            return(
                                <Row raised={true} children={'node'}>

                                        <TextField id="outlined-basic"  variant="outlined"
                                                   type="text"  name="text"
                                                   placeholder="enter text"
                                                   onChange={e=>handleinputchange(e,i)} />
                                    {
                                        inputList.length!==1 &&
                                        <Button  className={'form-control'} variant={'outlined'} onClick={()=> handleremove(i)}>Remove</Button>
                                    }
                                    { inputList.length-1===i &&
                                        <Button variant={'outlined'} onClick={ handleaddclick}>Add</Button>
                                    }
                                </Row>

                            );
                        } )}
                    <Button type={"submit"} variant={'outlined'} onClick={saveTexts}>Send</Button>
                </div>
            </div>
            }
        </Container>
       </div>
    );
}
export default Main;