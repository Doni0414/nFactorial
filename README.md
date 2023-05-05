# nFactorial
# Bigram Language Model
This project creates bigrams by reading from the input file and counting probabilities based on the number of bigrams appearing. It can generate random pseudo-names based on the probabilities of bigrams and supports further adding removing bigrams.

# Installation
To install project you should download this repository from git and open only the Bigram folder(Project folder) in IntelliJ IDE.

<img width="622" alt="image" src="https://user-images.githubusercontent.com/104587403/236528774-a69abe18-c89d-4f9a-b818-a28cddf82fa8.png">

# Runtime
1. After installation you can run the Main class. First, the program will ask you to enter the name of the input file where the dataset of names is stored. The input file should be stored in a folder together with java classes.

<img width="184" alt="image" src="https://user-images.githubusercontent.com/104587403/236529524-63e23bae-8043-43ac-a326-52db2399eca9.png">

2. Then a menu is displayed where you can select the following options:
1) Output the probabilities of bigrams
2) Generate a pseudo-name
3) A method that asks you to enter the number of names and generates as many names
4) Adding a new name. The new name will be divided into bigrams and will be added to the general list of bigrams
5) Deleting the name. Note, even if there is no such name, the count of bigrams of this word will still be decreased.
6) Exit from the program

<img width="268" alt="image" src="https://user-images.githubusercontent.com/104587403/236532106-088b3767-4484-4a59-9a60-4dcc43a1feb4.png">

That's the simple generator of pseudo-names, but I would like to add artificial intelligence already to generate real names.
