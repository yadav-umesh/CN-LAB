//source code java programming FileServent.java
import java.net.*;
import java.io.*;
public class FTP_server extends Thread
{
Socket socket;
String fileName;
BufferedReader in;
PrintWriter out;
FTP_server(Socket socket) throws IOException
{
this.socket = socket;
in = new BufferedReader(new
InputStreamReader(socket.getInputStream()));
out = new PrintWriter(new
OutputStreamWriter(socket.getOutputStream()));
}
public void run()
{
try
{
fileName = in.readLine();
File file = new File(fileName);
if (file.exists())
{
BufferedReader fileReader = new
BufferedReader(new FileReader(fileName));
String content = null;
while ((content = fileReader.readLine())
!=
null)
{
out.println(content);
out.flush();
}
System.out.println("File has been Sent...");
}
else
{
System.out.println("Requested File was Not Found...");
out.println("File Not Found");
out.flush();
}
socket.close();
System.out.println("Connection Closed!");
}
catch (FileNotFoundException e)
{
e.printStackTrace();
}
catch (IOException e)
{
e.printStackTrace();
}
}
}