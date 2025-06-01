import java.net.Socket;
import java.net.ServerSocket;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;


public class Server {
    public static void main(String[] args) throws IOException{
        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        serverSocket =new ServerSocket(1234);

        while(true){
            try{
                socket = serverSocket.accept();
                inputStreamReader = new InputStreamReader(socket.getInputStream());
                outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
                bufferedReader = new BufferedReader(inputStreamReader);
                bufferedWriter = new BufferedWriter(outputStreamWriter);

                while(true){
                    String msgReceived = bufferedReader.readLine();
                    System.out.println("Client: " + msgReceived);

                    bufferedWriter.write("message received");
                    bufferedWriter.newLine();
                    bufferedWriter.flush();

                    if(msgReceived.equalsIgnoreCase("BYE")) break;
                }

                socket.close();
                inputStreamReader.close();
                outputStreamWriter.close();
                bufferedReader.close();
                bufferedWriter.close();



            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
