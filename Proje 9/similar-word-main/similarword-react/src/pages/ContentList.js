import service from "../service/ContentService";
import {useEffect, useState} from "react";
import { Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from "@mui/material";
import Navbar from "./common/Navbar";


function ContentList() {

    const [contents,setContents]=useState([])

    useEffect(() => {
        service.getContents()
            .then(response => setContents(response.data))
            .catch(error => {
                console.log(error);
            })
    }, [])


    return(
        <div>
            <Navbar />
            <TableContainer component={Paper}>
                <Table sx={{backgroundColor:"#0000"}}
                       aria-label="simple table">
                    <TableHead>
                        <TableRow>
                            <TableCell sx={{ fontWeight: "bold"}}>ID</TableCell>
                            <TableCell sx={{ fontWeight: "bold"}} align="right">INPUTS</TableCell>
                            <TableCell sx={{ fontWeight: "bold"}} align="right">ELAPSED TIME&nbsp;(ns)</TableCell>
                            <TableCell sx={{ fontWeight: "bold"}} align="right">RESULT</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {contents.map((content) => (
                            <TableRow
                                key={content.id}
                            >
                                <TableCell sx={{ fontWeight: "italic"}} component="th" scope="row">
                                    {content.id}
                                </TableCell>
                                <TableCell sx={{ fontWeight: "italic"}} align="right">[{content.inputTexts.map(item => item) + " ]"}</TableCell>
                                <TableCell sx={{ fontWeight: "italic"}} align="right">{content.elapsedTime}</TableCell>
                                <TableCell sx={{ fontWeight: "italic"}} align="right">{content.result}</TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </div>
    );
}
export default ContentList;