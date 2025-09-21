
```bash
# Create output directory
mkdir -p out

# Compile all Java files
find . -name "*.java" -not -path "./out/*" -print | xargs javac -d out

# Run the application
java -cp out SnakesAndLaddersApplication
```

## ASCII Art UML Diagram

```
                                    ┌─────────────────┐
                                    │SnakesAndLadders │
                                    │   Application   │
                                    └────────┬────────┘
                                             │ uses
                     ┌───────────────────────┼───────────────────────┐
                     │                       │                       │
              ┌──────▼──────┐         ┌──────▼──────┐        ┌──────▼──────┐
              │    Game     │         │BoardFactory │        │    Dice     │
              │ (Singleton) │         │  (Factory)  │        │ (Strategy)  │
              └──────┬──────┘         └──────┬──────┘        └──────┬──────┘
                     │                       │                       │
         ┌───────────┼───────────┐           │               ┌──────┴──────┐
         │           │           │           │               │DiceStrategy │
    ┌────▼───┐  ┌───▼────┐ ┌───▼────┐  ┌───▼────┐         │ <<interface>>│
    │ Board  │  │ Player │ │Observer│  │ Board  │         └──────┬──────┘
    │(Builder)│  │        │ │Pattern │  │        │                │
    └────┬───┘  └────────┘ └────────┘  └───┬────┘         ┌──────┴──────┐
         │                                  │               │StandardDice │
    ┌────┴───────────┐                     │               │ LoadedDice  │
    │                │                     │               └─────────────┘
┌───▼────┐      ┌───▼────┐                │
│ Snake  │      │ Ladder │                │
│(Immut.)│      │(Immut.)│                │
└───┬────┘      └───┬────┘                │
    │               │                      │
    └───────┬───────┘                      │
            │                              │
       ┌────▼────┐                         │
       │Position │◄────────────────────────┘
       │(Immut.) │
       └─────────┘
```
