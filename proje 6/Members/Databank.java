package members;

public class Databank {

    String name ;
    Float oran ;
    public Databank(){

    }
    public Databank(String name ,Float oran ){
        this.name = name;
        this.oran = oran;
    }
    public String getName() {
        return name ;
    }

    public void setName(String name){
        this.name = name ;
    }

    public void setOran(Float oran){
        this.oran = oran ;
    }



    public Float getOran() {
        return oran ;
    }

}
