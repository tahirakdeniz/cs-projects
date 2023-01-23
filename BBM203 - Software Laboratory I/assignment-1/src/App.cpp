//
// Created by muham on 30.10.2022.
//

#include "App.h"
#include "MatrixCreator.h"
#include "TreasureFinder.h"
#include "OutputWriter.h"

void App::run() {
    // Create map Matrix
    MatrixCreator * mapMatrixCreator = new MatrixCreator(input->getMapInputFileName(),input->getMapMatrixRowNumber(), input->getMapMatrixColNumber());
    Matrix * mapMatrix = mapMatrixCreator->createMatrix();

    // Create key Matrix
    int keyMatrixSize = input->getKeyMatrixSize();
    MatrixCreator * keyMatrixCreator = new MatrixCreator(input->getKeyInputFileName(), keyMatrixSize, keyMatrixSize);
    Matrix * keyMatrix = keyMatrixCreator->createMatrix();

    // Create outputWriter
    OutputWriter * outputWriter = new OutputWriter(*input->getOutputFileName());

    // Find treasure chest
    TreasureFinder * treasureFinder = new TreasureFinder(mapMatrix, keyMatrix, outputWriter);
    treasureFinder->run();

    // deallocate memory
    delete mapMatrixCreator;
    delete mapMatrix;
    delete keyMatrixCreator;
    delete keyMatrix;
    delete treasureFinder;
    delete outputWriter;
}

App::App(Input *input) : input(input) {}

