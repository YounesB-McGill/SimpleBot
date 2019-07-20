package simplebot;

import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.LEADING;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextPane;

/**
 * This is the window where the chat will take place.
 */
public class ChatWindow {
  
  static JTextPane text;
  static JTextField input;
  
  static void appendToText(String input) {
    // TODO Append instead of replacing text
    text.setText(input);
  }
  
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
      }};
      
      input = new JTextField() {{
        setText("Type here...");
        Dimension d = new Dimension(400, 100);
        setMinimumSize(d);
        setMaximumSize(d);
        addActionListener((ActionEvent e) -> {
          appendToText(Chatbot.reply(getText()));
          paintAll(getGraphics());
        });
      }};
      
      getContentPane().setLayout(new GroupLayout(getContentPane()) {{
        setAutoCreateGaps(true);
        setHorizontalGroup(createSequentialGroup().addGroup(createParallelGroup(LEADING)
            .addComponent(text)
            .addComponent(input)
        ));
        setVerticalGroup(createSequentialGroup()
            .addGroup(createParallelGroup(BASELINE).addComponent(text))
            .addGroup(createParallelGroup(BASELINE).addComponent(input))
        );
      }});
      
      setVisible(true);
    }};
  }
}
