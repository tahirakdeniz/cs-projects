//
// Created by muham on 3.11.2022.
//

#ifndef ASSIGNMENT1_OUTPUTWRITER_H
#define ASSIGNMENT1_OUTPUTWRITER_H
#include <fstream>

class OutputWriter {
    std::ofstream * outputFile;

public:
    explicit OutputWriter(std::string &filename);
    void writeOutput(int centerRowIndex, int centerColumnIndex, int result);
    virtual ~OutputWriter();
};


#endif //ASSIGNMENT1_OUTPUTWRITER_H
