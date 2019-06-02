package client.controllers;

import java.io.IOException;

import static client.Utils.*;

public interface Transport {

    default void sendMessage(String string){
        try {
            bufferedWriter.write(string + "\n");
            bufferedWriter.flush();
        } catch (IOException exception){
            System.out.println(exception);
            //closeAplication();
        }
    }



//    default void closeAplication(){
//        if(socket != null){
//            try{
//                socket.close();
//            } catch (IOException ex){
//                System.out.println(ex);
//            }
//
//        }
//        if(bufferedReader != null){
//            try{
//                bufferedReader.close();
//            } catch (IOException ex){
//                System.out.println(ex);
//            }
//        }
//        if(bufferedWriter != null){
//            try{
//                bufferedWriter.close();
//            } catch (IOException ex){
//                System.out.println(ex);
//            }
//        }
        //System.exit(0);
   // }

}
