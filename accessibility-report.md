# Principle of Universal Design

### 1. Equitable Use
- We have currently have Text-to-Speech (TTS) feature to help people who are visually impaired to make use of our program.

### 2. Flexibility In Use
- Our program uses java swing, which is supported by all platforms.
- In addition, we implemented a discord bot so that people do not possess the software can use alternative.
- Our bot can be added to any group chats, and its service can be launched by anyone with our software.
- Players can choose specific win conditions (e.g., Tsumo, Riichi), accommodating diverse gameplay styles.

### 3. Simple and Intuitive Use
- Our GUI is considerably clear and easy to use, that all features are organized into several panels grouped by their functionality, and no second-level menu exists (so user don't have to switch in and out).
- Return from the score calculator are formatted into intuitively interpretable messages for detail information, together with the numeratic score.
- We can add multi-language support (which in fact, is partially done but not enabled since we didn't get time to fully complete it) so some other groups of people who don't understand English will be able to enjoy the program.

### 4. Perceptible Information

- Every tile is click-able and arranged in a visually propelling orientation.
- Tiles use high-contrast visuals aiding recognition for individuals with visual impairment
- Textual tabs for Tile Type, Round Wind, etc, ensure clarity of available actions.

### 5. Tolerance for Error
- Our tile selector automatically detects which tiles are not allowed to be selected by the mahjong rules, based on current Hand, Dora and Uradora configurations.
- For example, each tile is selected for at most 4 times before it is disabled from the tile selector, and the total number of tiles in Hand cannot exceed 14, that accords to the mahjong rues.
- This design helps to reduce the chance that the player accidentally adds an illegal tile to the Hand/Dora/Uradora lists.

### 6. Low physical effort
- Simple click dropdowns and erase reduce need for repetitive or complex actions
- Combinations of click-switch buttons reduced physical strain for users. 

### 7. Size and space for approach and use
- Everything is arranged in the same page with adequate spacing, ensuring usability of every function.
- Large interactive buttons improved experience for those with limited precision



# Target Audience
- We target specifically to Japanese Mahjong (Riichi) player.
- For beginner players and Mahjong teachers, our software can help to demonstrate scoring in an interactive way. This is crutial to newcomers who find Mahjong scording complex. 
- In addition, our software can also help tournaments organizer / online Mahjong Communities to calculate scores for different players. Especially with our extensive support on discord bot, different users can get feedback without installing the software. Real-time scoring and sharing can promote discussions about better strategy.

# Exclusion Analysis
- By the nature of our program, those without interest in Japeneses Mahjong are not likely to use this sofetware at all.
- This program assumes user understood terminologies of Japenese Mahjong settings and rule, IN ENGLISH. Those who is proficient in Mahjong but does not know vocabularies might be excluded.
- In addition, our Text To Speech only supports Japenese. Those with visual difficulties and do not distinguish Japanese sounds might not be benified
.
