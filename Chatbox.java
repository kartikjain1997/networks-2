/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatbox;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author J Girish
 */
public class Chatbox implements Runnable {

    /**
     * @param args the command line arguments
     */
    int x, y;
    Socket s;
    ArrayList<Socket> soc;
    JTextArea jTextArea;
    JLabel jlabel1, jlabel2;

    public Chatbox(int x, Socket s, JTextArea jTextArea, ArrayList<Socket> soc, JLabel jlabel1, JLabel jlabel2) {
        this.x = x;
        this.s = s;
        this.jTextArea = jTextArea;
        this.soc = soc;
        this.jlabel1 = jlabel1;
        this.jlabel2 = jlabel2;
    }

    public static void main(String[] args) {
        // TODO code application logic here
    }

    @Override
    public void run() {
        DataInputStream is = null;
        try {
            is = new DataInputStream(s.getInputStream());
            if (x == 0) {
                while (true) {

                    try {

                        String lol = is.readUTF();

                        System.out.println("clinet:" + lol);
                        jTextArea.append(lol + "\n");
                        for (int i = 0; i < soc.size(); i++) {
                            if (soc.get(i).getPort() == s.getPort() && soc.get(i).getInetAddress() == s.getInetAddress()) {
                            } else {
                                DataOutputStream d = new DataOutputStream(soc.get(i).getOutputStream());
                                d.writeUTF(lol);
                            }
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(Chatbox.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            } else if (x == 1) {
                System.out.println("welcome to thread condition x==1");
                while (true) {
                    try {
                        String lol = is.readUTF();
                        System.out.println("server:" + lol);
                        if (lol.equalsIgnoreCase("exit")) {
                            jlabel1.setVisible(true);
                            jlabel2.setText("Disconnected");
                            break;
                        } else {
                            System.out.println("server:" + lol);
                            jTextArea.append(lol + "\n");

                       // jTextArea.append("Client"+s.getPort()+":"+lol+"\n");
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(Chatbox.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
          //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (IOException ex) {
            Logger.getLogger(Chatbox.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                is.close();
            } catch (IOException ex) {
                Logger.getLogger(Chatbox.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
