//
// Created by muham on 25.12.2022.
//

#ifndef INC_203_ASS4_APP_H
#define INC_203_ASS4_APP_H


#include <fstream>
#include "PrimaryTree.h"
#include <vector>

/**
 * class to run the application
 */
class App {
    std::string inputFileName;
    std::string output1FileName;
    std::string output2FileName;
public:
    void run();
    App(const std::string &inputFile, const std::string &outputFile1, const std::string &outputFile2);

private:

    // apply commands to tree and writes to output when needed.
    void applyCommand(std::vector<std::string> command, PrimaryTree *tree, std::ofstream &output);
};


#endif //INC_203_ASS4_APP_H
