package test1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class SnackGame extends JFrame implements KeyListener, ActionListener {
    private final int TILE_SIZE = 20;
    private final int GRID_SIZE = 30;
    private final int GAME_WIDTH = TILE_SIZE * GRID_SIZE;
    private final int GAME_HEIGHT = TILE_SIZE * GRID_SIZE;
    
    private ArrayList<Point> snake;
    private Point food;
    private char direction;
    private boolean gameOver;
    private int score;
    private Timer timer;
    private Random random;
    private GamePanel gamePanel;
    
    public SnackGame() {
        setTitle("贪吃蛇游戏");
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        
        gamePanel = new GamePanel();
        add(gamePanel);
        gamePanel.addKeyListener(this);
        gamePanel.setFocusable(true);
        
        initGame();
    }
    
    private void initGame() {
        snake = new ArrayList<>();
        snake.add(new Point(GRID_SIZE / 2, GRID_SIZE / 2));
        direction = 'R';
        gameOver = false;
        score = 0;
        random = new Random();
        spawnFood();
        
        timer = new Timer(150, this);
        timer.start();
    }
    
    private void spawnFood() {
        int x, y;
        do {
            x = random.nextInt(GRID_SIZE);
            y = random.nextInt(GRID_SIZE);
        } while (snake.contains(new Point(x, y)));
        food = new Point(x, y);
    }
    
    private void move() {
        Point head = snake.get(0);
        Point newHead = new Point(head.x, head.y);
        
        switch (direction) {
            case 'U': newHead.y--; break;
            case 'D': newHead.y++; break;
            case 'L': newHead.x--; break;
            case 'R': newHead.x++; break;
        }
        
        if (newHead.x < 0 || newHead.x >= GRID_SIZE || 
            newHead.y < 0 || newHead.y >= GRID_SIZE) {
            gameOver = true;
            return;
        }
        
        if (snake.contains(newHead)) {
            gameOver = true;
            return;
        }
        
        snake.add(0, newHead);
        
        if (newHead.equals(food)) {
            score += 10;
            spawnFood();
        } else {
            snake.remove(snake.size() - 1);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            move();
            gamePanel.repaint();
        }
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        
        if (gameOver && key == KeyEvent.VK_SPACE) {
            initGame();
            gamePanel.repaint();
            return;
        }
        
        switch (key) {
            case KeyEvent.VK_UP:
                if (direction != 'D') direction = 'U';
                break;
            case KeyEvent.VK_DOWN:
                if (direction != 'U') direction = 'D';
                break;
            case KeyEvent.VK_LEFT:
                if (direction != 'R') direction = 'L';
                break;
            case KeyEvent.VK_RIGHT:
                if (direction != 'L') direction = 'R';
                break;
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}
    
    @Override
    public void keyReleased(KeyEvent e) {}
    
    private class GamePanel extends JPanel {
        public GamePanel() {
            setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
            
            g.setColor(Color.DARK_GRAY);
            for (int i = 0; i <= GRID_SIZE; i++) {
                g.drawLine(i * TILE_SIZE, 0, i * TILE_SIZE, GAME_HEIGHT);
                g.drawLine(0, i * TILE_SIZE, GAME_WIDTH, i * TILE_SIZE);
            }
            
            g.setColor(Color.RED);
            g.fillOval(food.x * TILE_SIZE + 2, food.y * TILE_SIZE + 2, 
                       TILE_SIZE - 4, TILE_SIZE - 4);
            
            for (int i = 0; i < snake.size(); i++) {
                Point segment = snake.get(i);
                if (i == 0) {
                    g.setColor(Color.GREEN);
                    g.fillRect(segment.x * TILE_SIZE, segment.y * TILE_SIZE, 
                               TILE_SIZE, TILE_SIZE);
                    g.setColor(Color.WHITE);
                    g.fillOval(segment.x * TILE_SIZE + 4, segment.y * TILE_SIZE + 4, 4, 4);
                    g.fillOval(segment.x * TILE_SIZE + 12, segment.y * TILE_SIZE + 4, 4, 4);
                } else {
                    g.setColor(new Color(0, 180, 0));
                    g.fillRect(segment.x * TILE_SIZE, segment.y * TILE_SIZE, 
                               TILE_SIZE, TILE_SIZE);
                }
            }
            
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 16));
            g.drawString("分数: " + score, 10, 20);
            
            if (gameOver) {
                g.setColor(Color.RED);
                g.setFont(new Font("Arial", Font.BOLD, 24));
                g.drawString("游戏结束!", GAME_WIDTH / 2 - 70, GAME_HEIGHT / 2);
                g.setFont(new Font("Arial", Font.PLAIN, 16));
                g.drawString("按空格键重新开始", GAME_WIDTH / 2 - 80, GAME_HEIGHT / 2 + 30);
                timer.stop();
            }
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SnackGame game = new SnackGame();
            game.setVisible(true);
        });
    }
}