
public class Main {
    public static void main(String[] args) {
        Reader reader = new Reader();
        TextProcessor textProcessor = new TextProcessor(reader.getAllFiles("C:\\Users\\andrada.marian\\Documents\\TP\\FilesToRead"));
        textProcessor.processAll();

    }
}
