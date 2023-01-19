package Widok;

import java.io.IOException;


public class Client  {



    public ClientConnection connection;

    public void setConnectionsClient()
    {
        this.connection.client = this;
    }




    public Client() throws IOException
    {
        this.connection = new ClientConnection();
        setConnectionsClient();

    }




    public static void main(String[] args)  throws IOException {

    }

}
