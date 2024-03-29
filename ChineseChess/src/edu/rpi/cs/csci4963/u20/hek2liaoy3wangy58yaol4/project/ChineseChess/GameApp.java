package edu.rpi.cs.csci4963.u20.hek2liaoy3wangy58yaol4.project.ChineseChess;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameApp {

    // private member variable
    public static String frameName;
    public static final int SERVER = 1;
    public static final int CLIENT = 0;
    public static ChessClient client;
    public static ChessServer server;
    public static final int WIN = -1;
    public static final int LOSE = -2;
    public static int STATE;
    public static int choose;
    public static GUI gui;

    /**
     * The function to choose client or server
     * @return the option number for client and server
     */
    public static int ChooseClientOrServer(){
        String[] options = {"Client", "Server"};
        return JOptionPane.showOptionDialog(null, "Please choose your option: ",
                "Click a button",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
    }

    /**
     * The method to send normal running message
     * @param boardInfo the board information in string[][] format
     */
    public static void sendRunningMessage(String[][] boardInfo){
        if (choose == CLIENT){
            client.sendRunningMessage(boardInfo);
        }else{
            server.sendRunningMessage(boardInfo);
        }
    }

    /**
     * The socket close method
     */
    public static void closeSocket(){
        if (choose == CLIENT){
            client.closeSocket();
        }else{
            server.closeServer();
        }
    }

    /**
     * The method to send terminate message
     * @param boardInfo the board information you want to use
     */
    public static void sendTerminateMessage(String[][] boardInfo){
    
        if (choose == CLIENT){
            client.sendTerminateMessage(boardInfo);
        }else{   
            server.sendTerminateMessage(boardInfo);
        }
    }

    /**
     * The method to send lose message
     * @param boardInfo the board information you want to use
     */
    public static void sendLoseMessage(String[][] boardInfo){
        if (choose == CLIENT){
            client.sendLoseMessage(boardInfo);

        }else{
            server.sendLoseMessage(boardInfo);
        }
    }

    /**
     * The main method to run the whole program
     * @param args the arguments you want to pass
     * @throws IOException when the input is invalid
     */
    public static void main(String[] args) throws IOException{
        try { // evaluate client and server method
            choose = ChooseClientOrServer();
            String serverName = "";
            if (choose == CLIENT) { // When the choose is client
                frameName = "Client";
                serverName = JOptionPane.showInputDialog("Please input your server name/address: ",
                        "localhost");
            }
            int portNumber = Integer.parseInt(JOptionPane.showInputDialog("Please input your port number: "));
            if (choose == SERVER) { // When the choose is server
                // server will go first
                frameName = "Server";
                server = new ChessServer(portNumber);
                server.start();
            } else {
                client = new ChessClient(serverName, portNumber);
                client.start();
            }

            gui = new GUI();
            if (choose == SERVER) { // When the choose is server
                gui.setServer();
                //gui.displayMsg("This is a server");
                // gui.setBoardMovable();
            }
        }catch (NumberFormatException e){

        }
    }
}
