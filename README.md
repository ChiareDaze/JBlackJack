# JBlackJack

A Java implementation of the classic Blackjack (21) game, developed to demonstrate fundamental Object-Oriented Programming (OOP) concepts.

## 🚀 Features
- **Complete Game Logic**: Full 52-card deck management with dynamic score calculation (including flexible Ace valuation: 1 or 11)
- **Automated Dealer**: Basic AI dealer following standard rules (hits until reaching at least 17)
- **Automated Bots**: Support for adding up to 3 autonomous bots to play concurrently alongside the player
- **Match Engine**: Immediate evaluation of wins, losses, ties (pushes), and busts

## 🛠️ Tech Stack & Concepts
- **Language**: Java (JDK 8 or higher)
- **OOP Principles**: Encapsulation, separation of concerns, modular class design and design patterns (`Card`, `Deck`, `Hand`, `Player`, `Bot`, `Game`).

## 📦 Getting Started

### Prerequisites
Ensure you have the [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/downloads/) installed.

### Installation & Execution

1. **Clone the repository**:
   
   ```bash
   git clone [[https://github.com/your-username/blackjack-java.git](https://github.com/your-username/blackjack-java.git)
   cd blackjack-java](https://github.com/ChiareDaze/JBlackJack.git)]
   ```

2. Open & Run:
   - Open the cloned project in your Java IDE
   - Navigate to `JBlackJack/src/controller/Main.java`
   - Run the `Main.java` file directly through your IDE's run button (or right-click > Run 'Main')

## 🎮 Game Rules

**Objective**: Get a hand total as close to 21 as possible without exceeding it, while beating the dealer's score.

### Card Values:
- 2 to 10: Face value
- J, Q, K: 10 points each
- Ace: 1 or 11 points, automatically calculated for the best outcome

