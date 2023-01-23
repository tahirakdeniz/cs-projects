//
// Created by muham on 3.11.2022.
//

#include "OutputWriter.h"

OutputWriter::OutputWriter(std::string &filename){
    outputFile = new std::ofstream(filename);
}

void OutputWriter::writeOutput(int centerRowIndex, int centerColumnIndex, int result) {
    *outputFile << centerRowIndex << "," << centerColumnIndex << ":" << result << std::endl;
}

OutputWriter::~OutputWriter() {
    outputFile->close();
    delete outputFile;
}


