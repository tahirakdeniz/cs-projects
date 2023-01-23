//
// Created by muham on 30.10.2022.
//

#ifndef ASSIGNMENT1_APP_H
#define ASSIGNMENT1_APP_H


#include "Input.h"

/*
 * an App class to run all other classes
 * */
class App {
    Input* input;
public:
    explicit App(Input *input);

    void run();
};


#endif //ASSIGNMENT1_APP_H
