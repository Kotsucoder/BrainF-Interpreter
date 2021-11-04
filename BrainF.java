import java.util.Scanner;

class BrainF {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String codeString = "";
        System.out.println("Please input code here:");
        codeString = input.nextLine();

        String[] code = new String[codeString.length()];

        for(int i = 0; i < codeString.length(); i++) {
            code[i] = codeString.substring(i, i + 1);
        }

        int[] brackets = new int[code.length];
        int[] marker = new int[code.length];
        int mark = 0;
        for(int i = 0; i < code.length; i++) {
            if(code[i].equals("[")) {
                marker[i] = mark;
                mark++;
            }
            else if(code[i].equals("]")) {
                mark--;
                for(int y = 0; y < i; y++) {
                    if(marker[y] == mark) {
                        marker[y] = -1;
                        brackets[y] = i;
                        brackets[i] = y;
                    }
                }
                marker[i] = -1;
            }
            else {
                marker[i] = -1;
            }
        }

        int[] memory = new int[30000];
        for(int i = 0; i < memory.length; i++) {
            memory[i] = 0;
        }

        int memPointer = 0;

        for(int codePointer = 0; codePointer < code.length; codePointer++) {
            switch(code[codePointer]) {
                case ">":
                    memPointer++;
                    if(memPointer >= memory.length) {
                        memPointer = 0;
                    }
                    break;
                case "<":
                    memPointer--;
                    if(memPointer < 0) {
                        memPointer = memory.length - 1;
                    }
                    break;
                case "+":
                    if(memory[memPointer] == 255) {
                        memory[memPointer] = 0;
                    }
                    else {
                        memory[memPointer]++;
                    }
                    break;
                case "-":
                    if(memory[memPointer] == 0) {
                        memory[memPointer] = 255;
                    }
                    else {
                        memory[memPointer]--;
                    }
                    break;
                case ".":
                    System.out.print((char)(memory[memPointer]));
                    break;
                case ",":
                    System.out.println();
                    System.out.print(">");
                    String usrinp = input.nextLine();
                    char tempChar = usrinp.charAt(0);
                    memory[memPointer] = (int)(tempChar);
                    break;
                case "[":
                    if(memory[memPointer] == 0) {
                        codePointer = brackets[codePointer];
                    }
                    break;
                case "]":
                    if(memory[memPointer] != 0) {
                        codePointer = brackets[codePointer];
                    }
                    break;
                default:
                    break;
            }
        }

        input.close();
    }
}