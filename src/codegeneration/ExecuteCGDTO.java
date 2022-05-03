package codegeneration;

public class ExecuteCGDTO {
    int bytesReturn;
    int bytesLocal;
    int bytesParam;

    public ExecuteCGDTO(int bytesReturn, int bytesLocal, int bytesParam){
        this.bytesReturn = bytesReturn;
        this.bytesLocal = bytesLocal;
        this.bytesParam = bytesParam;
    }
}
