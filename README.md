# The-Gorgon

## Overview  
A classic **Snake Game** built with Java Swing, featuring smooth controls, score tracking, and collision detection. Perfect for demonstrating OOP principles and game development fundamentals.

![Gameplay Demo](https://via.placeholder.com/800x500/333333/FFFFFF?text=Snake+Gameplay+GIF)  

## Features  
- **Intuitive Controls**: Arrow keys for snake navigation  
- **Score System**: Tracks apples eaten with live score updates  
- **Collision Detection**: Handles wall/self-collision with game over screen  
- **Dynamic Spawning**: Apples spawn randomly, avoiding snake body  
- **State Management**: Start screen, gameplay, and game over states  

## How It Works  
1. **Launch** the game from `GameController.java`  
2. **Click "Start"** or press any key to begin  
3. **Control the snake** using arrow keys  
4. **Eat apples** to grow longer and increase score  
5. **Avoid collisions** with walls or yourself!  

## 🛠️ Architecture  
```mermaid
graph TD
    A[GameController] -->|Updates| B[Model]
    B -->|Snake/Apple Data| C[View]
    C -->|Renders| D[GamePanel]
    D -->|User Input| A
