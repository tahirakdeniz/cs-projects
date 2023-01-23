//
// Created by muham on 22.11.2022.
//

#ifndef ASS203_2_WRITER_H
#define ASS203_2_WRITER_H


#include <fstream>
#include "ApartmentList.h"

/**
 * a class to write output file.
 */
class Writer {
    ApartmentList * apartmentList;
    ofstream * outputStream;
public:
    explicit Writer(ApartmentList *apartmentList, const string &outputFileName);
    void findSumOfMaxBandwidth();
    void listApartments();

    virtual ~Writer();
};


#endif //ASS203_2_WRITER_H
