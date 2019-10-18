import java.awt.*;
import javax.swing.*;

public class Panel extends javax.swing.JFrame {
    
    private int turn;
    private final JButton[] buttons;
    
    public Panel() {
        turn = 0;
        int i = 0;
        buttons = new JButton[9];
        String btnName = "B1";
        initComponents();
        while(i != 9){
            for (Component c : this.getContentPane().getComponents()){
                if(c instanceof JButton){
                    if(btnName.equals(c.getName())){
                        buttons[i] = (JButton) c;
                        i++;
                        btnName = btnName.replaceAll(String.valueOf(i), String.valueOf(i+1));
                    }
                }
            }   
        }
    }
    
    private void clicked(java.awt.event.ActionEvent evt){
        int winner;
        JButton t = (JButton)evt.getSource();
        if(turn % 2 == 0){
            t.setText("X");
        }
        else{
            t.setText("O");
        }
        t.setEnabled(false);
        turn += 1;
        
        winner = checkWinner();
        int score;
        if(winner == 1){
            score = Integer.valueOf(P1Score.getText());
            score += 1;
            P1Score.setText(String.valueOf(score));
            clear();
        }
        else if(winner == 2){
            score = Integer.valueOf(P2Score.getText());
            score += 1;
            P2Score.setText(String.valueOf(score));
            clear();
        }
        
        if(isFinished()){
            clear();
        }
        
    }
    
    private boolean isFinished(){
        for (JButton button : buttons) {
            if (button.isEnabled() == true) {
                return false;
            }
        }
        return true;
    }
    
    private int checkWinner(){
        for(int i=0; i<3; i++){ // Check horizontal
            if("X".equals(buttons[i].getText()) && "X".equals(buttons[i+3].getText()) && "X".equals(buttons[i+6].getText())){
                return 1; 
            }
            if("O".equals(buttons[i].getText()) && "O".equals(buttons[i+3].getText()) && "O".equals(buttons[i+6].getText())){
                return 2; 
            }
        }
        for(int i=0; i<=6; i+=3){ // Check vertical
            if("X".equals(buttons[i].getText()) && "X".equals(buttons[i+1].getText()) && "X".equals(buttons[i+2].getText())){
                return 1; 
            }
            if("O".equals(buttons[i].getText()) && "O".equals(buttons[i+1].getText()) && "O".equals(buttons[i+2].getText())){
                return 2; 
            }
        }
        // Check diagonal from left
        if("X".equals(buttons[0].getText()) && "X".equals(buttons[4].getText()) && "X".equals(buttons[8].getText())){
                return 1; 
        }
        if("O".equals(buttons[0].getText()) && "O".equals(buttons[4].getText()) && "O".equals(buttons[8].getText())){
                return 2; 
        }
        // Check diagonal from right
        if("X".equals(buttons[6].getText()) && "X".equals(buttons[4].getText()) && "X".equals(buttons[2].getText())){
                return 1; 
        }
        if("O".equals(buttons[6].getText()) && "O".equals(buttons[4].getText()) && "O".equals(buttons[2].getText())){
                return 2; 
        }
        return 0;
    }
    
    private void clear(){
        turn = 0;
        for (JButton button : buttons) {
            button.setText("");
            button.setEnabled(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        P1Score = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        P2Score = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        B1 = new javax.swing.JButton();
        B2 = new javax.swing.JButton();
        B3 = new javax.swing.JButton();
        B4 = new javax.swing.JButton();
        B5 = new javax.swing.JButton();
        B6 = new javax.swing.JButton();
        B7 = new javax.swing.JButton();
        B8 = new javax.swing.JButton();
        B9 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TicTacToe");
        setResizable(false);

        P1Score.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        P1Score.setForeground(new java.awt.Color(255, 0, 0));
        P1Score.setText("0");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Player 1");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("-");

        P2Score.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        P2Score.setForeground(new java.awt.Color(255, 0, 0));
        P2Score.setText("0");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setText("Player 2");

        B1.setName("B1"); // NOI18N
        B1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B1ActionPerformed(evt);
            }
        });

        B2.setName("B2"); // NOI18N
        B2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B2ActionPerformed(evt);
            }
        });

        B3.setName("B3"); // NOI18N
        B3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B3ActionPerformed(evt);
            }
        });

        B4.setName("B4"); // NOI18N
        B4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B4ActionPerformed(evt);
            }
        });

        B5.setName("B5"); // NOI18N
        B5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B5ActionPerformed(evt);
            }
        });

        B6.setName("B6"); // NOI18N
        B6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B6ActionPerformed(evt);
            }
        });

        B7.setName("B9"); // NOI18N
        B7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B7ActionPerformed(evt);
            }
        });

        B8.setName("B7"); // NOI18N
        B8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B8ActionPerformed(evt);
            }
        });

        B9.setName("B8"); // NOI18N
        B9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(B3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(P1Score)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(P2Score)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(B5, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                                .addComponent(B6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(B4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(B9, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(B8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(B7, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(P1Score, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(P2Score)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(B1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(B2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(B4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(B8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(B5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(B9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(B3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    private void B1ActionPerformed(java.awt.event.ActionEvent evt) {                                   
        clicked(evt);
    }                                  

    private void B2ActionPerformed(java.awt.event.ActionEvent evt) {                                   
        clicked(evt);
    }                                  

    private void B3ActionPerformed(java.awt.event.ActionEvent evt) {                                   
        clicked(evt);
    }                                  

    private void B4ActionPerformed(java.awt.event.ActionEvent evt) {                                   
        clicked(evt);
    }                                  

    private void B5ActionPerformed(java.awt.event.ActionEvent evt) {                                   
        clicked(evt);
    }                                  

    private void B6ActionPerformed(java.awt.event.ActionEvent evt) {                                   
        clicked(evt);
    }                                  

    private void B7ActionPerformed(java.awt.event.ActionEvent evt) {                                   
        clicked(evt);
    }                                  

    private void B8ActionPerformed(java.awt.event.ActionEvent evt) {                                   
        clicked(evt);
    }                                  

    private void B9ActionPerformed(java.awt.event.ActionEvent evt) {                                   
        clicked(evt);
    }                                  

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new Panel().setVisible(true);
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton B1;
    private javax.swing.JButton B2;
    private javax.swing.JButton B3;
    private javax.swing.JButton B4;
    private javax.swing.JButton B5;
    private javax.swing.JButton B6;
    private javax.swing.JButton B7;
    private javax.swing.JButton B8;
    private javax.swing.JButton B9;
    private javax.swing.JLabel P1Score;
    private javax.swing.JLabel P2Score;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration                   
}
