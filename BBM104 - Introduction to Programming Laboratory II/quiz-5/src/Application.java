public class Application {
    private FileManager inputFileManager;
    private FileManager outputFileManager;

    Application(FileManager inputFileManager, FileManager outputFileManager){

        this.inputFileManager = inputFileManager;
        this.outputFileManager = outputFileManager;
    }

    public void run() {
        Queue queue = new Queue();

        while (inputFileManager.hasNextLine()){
            String line = inputFileManager.nextLine().trim();

            try {
                Stack stack = new Stack(99);


                int number = Integer.parseInt(line);
                int secondNumber = number/8;
                int reminder = number - secondNumber*8;

                while (number > 0){
                    stack.push(reminder);
                    number = secondNumber;
                    secondNumber = number/8;
                    reminder = number - secondNumber*8;
                }

                outputFileManager.write(stack.toString());

            }
            catch (NumberFormatException e){

                String[] arr = line.split(" ");
                String command = arr[0];

                try {
                    int number = Integer.parseInt(arr[1]);

                    switch (command) {
                        case "enqueFront":
                            queue.enqueueFront(number);
                            break;
                        case "enqueMiddle":
                            queue.enqueueMiddle(number);
                            break;
                        case "enqueBack":
                            queue.enqueueBack(number);
                            break;
                        default:
                    }
                }
                catch (IndexOutOfBoundsException e1){
                    switch (command) {
                        case "dequeFront":
                            queue.dequeueFront();
                            break;
                        case "dequeMiddle":
                            queue.dequeueMiddle();
                            break;
                        case "dequeBack":
                            queue.dequeueBack();
                            break;
                        default:
                            break;
                    }
                }

                outputFileManager.write(queue.toString());
            }
        }
    }
}
