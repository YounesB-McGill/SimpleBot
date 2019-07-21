package simplebot;

import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.LEADING;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

/**
 * This is the window where the chat will take place.
 */
public class ChatWindow {
  
  static JTextPane text;
  static JTextField input;
  
  static void appendToText(String input) {    
    String[] chatWindowText = text.getText().split("</body>");
    text.setText(chatWindowText[0] + "<p>" + input + "</p></body>" + chatWindowText[1]);
  }
  
  /**
   * Shows window to user.
   * 
   * 
   * <p>Note: This method does not respect all coding conventions. Be careful if you use this code!
   */
  @SuppressWarnings("serial")
  static void show() {
    new JFrame("SimpleBot") {{
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(450, 700);
      
      text = new JTextPane() {{
        setContentType("text/html");
        Dimension d = new Dimension(400, 550);
        setMinimumSize(d);
        setMaximumSize(d);
        setAutoscrolls(true);
      }};
      
      var scrollPane = new JScrollPane(text);
      
      input = new JTextField() {{
        setText("Type here!");
        var scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.schedule(() -> { setText(""); }, 500, TimeUnit.MILLISECONDS);
        Dimension d = new Dimension(400, 100);
        setMinimumSize(d);
        setMaximumSize(d);
        addActionListener((ActionEvent e) -> {
          appendToText(Chatbot.reply(getText()));
          setText("");
          paintAll(getGraphics());
        });
      }};
      
      getContentPane().setLayout(new GroupLayout(getContentPane()) {{
        setAutoCreateGaps(true);
        setHorizontalGroup(createSequentialGroup().addGroup(createParallelGroup(LEADING)
            .addComponent(scrollPane)
            .addComponent(input)
        ));
        setVerticalGroup(createSequentialGroup()
            .addGroup(createParallelGroup(BASELINE).addComponent(scrollPane))
            .addGroup(createParallelGroup(BASELINE).addComponent(input))
        );
      }});
      
      setVisible(true);
    }};
  }
  
}
