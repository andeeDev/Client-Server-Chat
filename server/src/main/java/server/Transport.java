package server;

import java.io.IOException;
import static server.ClientThread.*;

public interface Transport {

    /*default void sendMessage(String string){
        try {
            bufferedWriter.write(string + "\n");
            bufferedWriter.flush();
        } catch (IOException exception){
            System.out.println(exception);
//                closeAplication();
        }
    }

    default String getResponseMessage(){
        try {
            Thread.sleep(50);
            String str = bufferedReader.readLine();
            return str;
        } catch (IOException | InterruptedException exception){
            System.out.println(exception);
//                closeAplication();
        }
        return null;
    }*/

}
