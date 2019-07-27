import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Color;

public class GamePanel extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GamePanel frame = new GamePanel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GamePanel() {
		setBackground(Color.BLACK);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 880, 600);
		setFocusable(false);
		Panel contentPane = new Panel();
		setContentPane(contentPane);
		
	}

}
