package DataTransferObject;

public class Structure {
    public String message;
    public int number;
    public boolean Flag;

    public Structure(String message, int number, boolean Flag){
        this.message = message;
        this.number = number;
        this.Flag = Flag;
    }

    @Override
    public String toString() {
        return "Structure{" +
                "message='" + message + '\'' +
                ", number=" + number +
                '}';
    }
}
