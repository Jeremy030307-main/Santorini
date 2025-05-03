# FIT3077 Repository

# Santorini Board Game (Java Implementation)

This project is a digital implementation of the board game **Santorini**, built in Java using the MVC (Model-View-Controller) architectural pattern. The game currently supports two-player gameplay, basic rules, and special God powers like **Artemis** and **Demeter**, as defined in the Edition Two ruleset.

## 📁 Project Structure

```text
src/
└── main/
    └── java/
        ├── Controller/         # Handles game logic, flow control, and UI events
        │   ├── GameFlow/
        │   ├── ChallengerChoose... (God selection)
        │   ├── HomeController.java
        │   ├── SetupController.java
        │   └── ...
        ├── Model/              # Core game logic and data classes
        │   ├── Action/
        │   ├── Board/
        │   ├── Game/
        │   ├── GameRule/
        │   ├── GodCard/
        │   └── Player/
        ├── View/               # GUI components and UI rendering
        │   ├── Game/
        │   ├── GameOver/
        │   ├── Home/
        │   ├── Setup/
        │   └── ...
        └── Main.java           # Application entry point


## 🎮 Features Implemented

- 🌊 **Ocean-themed GUI** with tile-based board
- 🧱 **Basic gameplay mechanics**: move, build, and win conditions
- 🧙‍♂️ **God Powers**: Artemis and Demeter
- 👤 **Two-player support**
- 🧩 **Turn-based interface** with clickable actions
- 🎨 **Figma-inspired GUI design**, mimicking physical gameplay elements

## 🚀 How to Run

1. Clone the repository.
2. Open in your preferred Java IDE (e.g., IntelliJ IDEA).
3. Run `Main.java`.

Ensure Java 24 is installed.

## 🛠 Technologies Used

- Java
- Swing (for GUI)
- MVC Design Pattern
- Figma (for UI design references)

## 📅 Current Sprint: Sprint 2

- ✅ Functional GUI-based game for two players
- ✅ God power selection and usage (limited set)
- ✅ Turn logic, building mechanics, and win/loss detection

## 🔜 Upcoming in Sprint 3

- Flexible board configurations (non-rectangular)
- Support for 3–4 players
- Different tile types
- Save/load game functionality

## 📄 License

This project is for educational purposes and not intended for commercial distribution.

---