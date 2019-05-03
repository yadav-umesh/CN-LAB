//source code java programming ClientFile.java
import java.io.*;
import java.net.*;
public class FTP_client
{
String serverAddress;
String fileName;
int port;
Socket socket;
FTP_client()
{
this("localhost", 9999, "Model.txt");
}
FTP_client(String serverAddress, int port, String
fileName)
{
this.serverAddress = serverAddress;
this.port = port;
this.fileName = fileName;
}
void sendRequestForFile() throws UnknownHostException,
IOException
{
socket = new Socket(serverAddress, port);
System.out.println("Connecting to Server...");
PrintWriter writer = new PrintWriter(new
OutputStreamWriter(socket.getOutputStream()));
writer.println(fileName);
writer.flush();
System.out.println("Request has been Sent... ");
getResponseFromServer();
socket.close();
}
void getResponseFromServer() throws IOException
{
BufferedReader reader = new BufferedReader(new
InputStreamReader(socket.getInputStream()));
String response = reader.readLine();
if(response.trim().toLowerCase().equals("filenotfound"))
{
System.out.println(response);
return; }
else
{
BufferedWriter fileWriter = new
BufferedWriter(new
FileWriter("FileRecd.txt"));
do
{
fileWriter.write(response);
fileWriter.flush();

}while((response=reader.readLine())!=null);
fileWriter.close();
}
}
public static void main(String[] args)
{
try
{
new FTP_client().sendRequestForFile();
}
catch (UnknownHostException er)
{
er.printStackTrace();
}
catch (IOException er)
{
er.printStackTrace();
}
}
}