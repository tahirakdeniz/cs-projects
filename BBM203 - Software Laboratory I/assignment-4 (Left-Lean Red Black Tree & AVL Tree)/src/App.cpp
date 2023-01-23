//
// Created by muham on 25.12.2022.
//

#include <sstream>
#include <iostream>
#include "App.h"

App::App(const std::string &inputFile, const std::string &outputFile1, const std::string &outputFile2) {
    inputFileName = inputFile;
    output1FileName = outputFile1;
    output2FileName = outputFile2;
}

void App::run() {
    // Create streams:
    std::ifstream inputFile(inputFileName);
    std::ofstream output1(output1FileName);
    std::ofstream output2(output2FileName);

    // Create Trees:
    auto avlTree = new PrimaryTree(AVL_TREE);
    auto rbtree = new PrimaryTree(RED_BLACK_TREE);

    // Read input file
    std::string line;
    while (getline(inputFile, line)) {
        // Split the line
        std::vector<std::string> commands;

        std::stringstream ss(line);
        std::string word;
        while (!ss.eof()) {
            getline(ss, word, '\t');
            commands.push_back(word);
        }

        // APPLY COMMANDS:
        applyCommand(commands, avlTree, output1);
        applyCommand(commands, rbtree, output2);
    }

    delete avlTree;
    delete rbtree;
}


void App::applyCommand(std::vector<std::string> command, PrimaryTree *tree, std::ofstream &output) {
    std::string commandName = command[0];

    if(commandName == "insert" || commandName == "updateData")
        tree->put(command[1], command[2], std::stoi(command[3]));

    else if(commandName == "remove") {
        std::string category = command[1];
        std::string name = command[2];

        tree->remove(category, name);
    }

    else if(commandName == "printAllItems") {
        output << "command:" << commandName << std::endl;
        output << tree->toStringAll();
    }

    else if(commandName == "printAllItemsInCategory") {
        std::string category = command[1];
        output << "command:" << commandName << '\t' << category << std::endl;
        output << tree->toStringCategory(category);
    }

    else if(commandName == "printItem" || commandName == "find") {

        std::string category = command[1];
        std::string name = command[2];
        output << "command:" << commandName << '\t' <<  category << '\t' << name << std::endl;
        output << tree->toStringItem(category, name);
    }
}


