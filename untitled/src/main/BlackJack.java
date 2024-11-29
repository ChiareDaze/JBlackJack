/*
package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class BlackJack {

    ArrayList<Card> deck; //deck of cards
    Random random = new Random(); //shuffle deck

    //dealer
    Card hiddenCard;
    ArrayList<Card> dealerHand;
    int dealerSum;
    int dealerAceCount;

    //player
    ArrayList<Card> playerHand;
    int playerSum;
    int playerAceCount;

    public BlackJack() {
        startGame();

        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gamePanel.setLayout(new BorderLayout());
        gamePanel.setBackground(new Color(53,101,77));
        frame.add(gamePanel);

        hitButton.setFocusable(false);
        buttonPanel.add(hitButton);
        stayButton.setFocusable(false);
        buttonPanel.add(stayButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        hitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Card card = deck.remove(deck.size()-1);
                playerSum += card.getValue();
                if (card.isAce()) playerAceCount++;
                playerHand.add(card);
                if (reducePlayerAce() > 21){
                    hitButton.setEnabled(false);
                }


                gamePanel.repaint(); //calls paintComponent
            }
        });
        gamePanel.repaint();

        stayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hitButton.setEnabled(false);
                stayButton.setEnabled(false);

                while (dealerSum < 17){
                    Card card = deck.remove(deck.size()-1);
                    dealerSum += card.getValue();
                    if (card.isAce()) dealerAceCount++;
                    dealerHand.add(card);
                    reduceDealerAce();
                }
                gamePanel.repaint();
            }
        });
    }

    public void startGame() {
        //deck
        buildDeck();
        shuffleDeck();

        //dealer
        dealerHand = new ArrayList<Card>();
        dealerSum = 0;
        dealerAceCount = 0;

        hiddenCard = deck.remove(deck.size()-1); //remove the last card from the deck
        dealerSum += hiddenCard.getValue();
        if (hiddenCard.isAce()) dealerAceCount++;

        Card card = deck.remove(deck.size()-1);
        dealerSum += card.getValue();
        if (card.isAce()) dealerAceCount++;
        dealerHand.add(card);

        System.out.println("Dealer;");
        System.out.println("Hidden cards.Card: " + hiddenCard);
        System.out.println("DealerHand" + dealerHand);
        System.out.println("DealerSum: " + dealerSum);
        System.out.println("DealerAceCount: " + dealerAceCount);

        //player
        playerHand = new ArrayList<Card>();
        playerSum = 0;
        playerAceCount = 0;
        for (int i = 0; i < 2; i++){
            card = deck.remove(deck.size()-1);
            playerSum += card.getValue();
            if (card.isAce()) playerAceCount++;
            playerHand.add(card);
        }

        System.out.println("Player:");
        System.out.println("PlayerHand: " + playerHand);
        System.out.println("PlayerSum: " + playerSum);
        System.out.println("PlayerAceCount: " + playerAceCount);
    }

    public void buildDeck(){
        deck = new ArrayList<>();
        String[] values = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
        String[] types = {"H","D","C","S"};

        //iterate through all values and types to create a deck of cards
        for (String value : values) {
            for (String type : types) {
                deck.add(new Card(value, type));
            }
        }
        System.out.println("built deck");
        System.out.println(deck);
    }

    public void shuffleDeck(){
        for (int i = 0; i<deck.size(); i++){
            int j = random.nextInt(deck.size()); //gives a random number between 0 and 51
            Card currCard = deck.get(i);
            Card randomCard = deck.get(j);
            deck.set(i, randomCard);
            deck.set(j, currCard);
        }
        System.out.println("shuffled deck");
        System.out.println(deck);
    }

    //window
    int boardWidth = 600;
    int boardHeight = boardWidth;

    int cardWidth = 110; //ratio should 1/1.4
    int cardHeight = 154;

    JFrame frame = new JFrame("main.BlackJack");
    JPanel gamePanel = new JPanel(){
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            try {
                //draw hidden card
                Image hiddenCardImage = new ImageIcon(getClass().getResource("cards/BACK.png")).getImage();
                if (!stayButton.isEnabled()){
                    hiddenCardImage = new ImageIcon(getClass().getResource(hiddenCard.getImagePath())).getImage();
                }
                g.drawImage(hiddenCardImage, 20, 20, cardWidth, cardHeight, null);

                //draw dealer's hand
                for (int i = 0; i < dealerHand.size(); i++){
                    Card card = dealerHand.get(i);
                    Image CardImg = new ImageIcon (getClass().getResource(card.getImagePath())).getImage();
                    g.drawImage(CardImg, cardWidth + 25 + (cardWidth + 5) * i, 20, cardWidth, cardHeight, null);
                }

                //draw player's hand
                for (int i = 0; i < playerHand.size(); i++){
                    Card card = playerHand.get(i);
                    Image CardImg = new ImageIcon (getClass().getResource(card.getImagePath())).getImage();
                    g.drawImage(CardImg, 20 + (cardWidth + 5) * i, 320, cardWidth, cardHeight, null);

                }

                if (!stayButton.isEnabled()){
                    dealerSum = reduceDealerAce();
                    playerSum = reducePlayerAce();
                    System.out.println("STAY:");
                    System.out.println(dealerSum);
                    System.out.println(playerSum);

                    String message = "";
                    if (playerSum > 21){
                        message = "You Lose!";
                    } else if (dealerSum > 21){
                        message = "You Win!";
                    } else if (playerSum > dealerSum){
                        message = "You Win!";
                    } else if (playerSum < dealerSum){
                        message = "You Lose!";
                    } else {
                        message = "Tie!";
                    }
                    g.setFont(new Font ("Arial", Font.PLAIN, 30));
                    g.setColor(Color.white);
                    g.drawString(message, 220, 250);
                    
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    JPanel buttonPanel = new JPanel();
    JButton hitButton = new JButton("Hit");
    JButton stayButton = new JButton("Stay");

    public int reducePlayerAce(){ //when the points are over 21, reduce the value of the ace from 11 to 1
        while (playerSum > 21 && playerAceCount > 0){
            playerSum -= 10;
            playerAceCount--;
        }
        return playerSum;
    }

    public int reduceDealerAce(){ //when the points are over 21, reduce the value of the ace from 11 to 1
        while (dealerSum > 21 && dealerAceCount > 0){
            dealerSum -= 10;
            dealerAceCount--;
        }
        return dealerSum;
    }
}

*/