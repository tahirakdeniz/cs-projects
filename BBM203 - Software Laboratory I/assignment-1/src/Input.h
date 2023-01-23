//
// Created by muham on 30.10.2022.
//

#ifndef ASSIGNMENT1_INPUT_H
#define ASSIGNMENT1_INPUT_H


#include <string>

/**
 * a class to get and represent input variables.
 */
class Input {
    int mapMatrixRowNumber;
    int mapMatrixColNumber;
    int keyMatrixSize;
    std::string *mapInputFileName;
    std::string *keyInputFileName;
    std::string *outputFileName;
public:
    explicit Input(char * argv[]);

    int getMapMatrixRowNumber() const;

    int getMapMatrixColNumber() const;

    int getKeyMatrixSize() const;

    std::string *getMapInputFileName() const;

    std::string *getKeyInputFileName() const;

    std::string *getOutputFileName() const;

    virtual ~Input();
};

#endif //ASSIGNMENT1_INPUT_H
