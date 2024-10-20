import java.util.Stack;
import java.util.Scanner;

public class TextEditor{
    private Stack<String> textHistory;
    private Stack<String> redoStack;

    public TextEditor() {
        textHistory = new Stack<>();
        redoStack = new Stack<>();
        textHistory.push("");
    }

    public void write(String newText) {
        textHistory.push(textHistory.peek() + newText);
        redoStack.clear();
    }

    public void undo() {
        if (textHistory.size() > 1) {
            redoStack.push(textHistory.pop());
        } else {
            System.out.println("Tidak ada yang bisa di-undo.");
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            textHistory.push(redoStack.pop());
        } else {
            System.out.println("Tidak ada yang bisa di-redo.");
        }
    }

    public void show() {
        System.out.println("Teks saat ini: " + textHistory.peek());
    }

    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        Scanner scanner = new Scanner(System.in);
        String command;

        System.out.println(" Text Editor (ketik 'exit' untuk keluar)");
        while (true) {
            System.out.print("\nPerintah (write, undo, redo, show): ");
            command = scanner.nextLine().trim();

            if (command.equalsIgnoreCase("exit")) {
                break;
            }

            switch (command.toLowerCase()) {
                case "write":
                    System.out.print("Masukkan teks: ");
                    String text = scanner.nextLine();
                    editor.write(text);
                    break;

                case "undo":
                    editor.undo();
                    break;

                case "redo":
                    editor.redo();
                    break;

                case "show":
                    editor.show();
                    break;

                default:
                    System.out.println("Perintah tidak dikenal.");
            }
        }

        scanner.close();
    }
}
