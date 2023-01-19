package Widok;

import java.io.*;
import java.net.Socket;

/**
 * ClientConnection class is responsible for connection between user and server.
 */
public class ClientConnection  {
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;

    Client client;


    public ClientConnection() throws IOException
    {
        this.socket = new Socket("localhost", 1234);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        listenToServer();
    }


    public void sendMoves(String move) throws IOException
    /**
     * method sends move to server
     */
    {
        try
        {
            if(socket.isConnected())
            {
                out.write(move);
                out.newLine();
                out.flush();
            }


        } catch (IOException e)
        {
            System.err.println("Problem with sending moves...");

        }
    }

    public void listenToServer() throws IOException
    /**
     * method waits for message from server and delegates it to client.setGameState method
     */
    {

        new Thread(new Runnable() {
            @Override
            public void run() {
                String msg = "";
                try
                {
                    while(true)
                    {
                        msg = in.readLine();
                        String[] coordinates = msg.split(" ");
                        client.setGameState(msg);

                        if(!coordinates[65].equals("0")) break;
                    }


                } catch (IOException e)
                {
                    System.err.println("Problem with White's connection...");
                }

            }
        }).start();
    }

}
