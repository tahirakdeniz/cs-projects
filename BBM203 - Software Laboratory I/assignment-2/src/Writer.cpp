//
// Created by muham on 22.11.2022.
//

#include "Writer.h"

Writer::Writer(ApartmentList *apartmentList, const string &outputFileName) : apartmentList(apartmentList) {
    auto stream = new ofstream();
    stream->open(outputFileName);
    outputStream = stream;
}

void Writer::findSumOfMaxBandwidth() {
    int sumOfMaxBandwidth = apartmentList->getSumOfMaxBandwidths();
    *outputStream <<"sum of bandwidth: " <<sumOfMaxBandwidth << "\n\n";
}

void Writer::listApartments() {
    ApartmentNode *apartmentHead = apartmentList->getHead();
    if(!apartmentHead) {
        *outputStream << "NULL\n";
        return;
    }

    ApartmentNode * node = apartmentHead;
    do {
        *outputStream << node->getName()
            << '(' << node->getMaxBandwidth() << ')' << "\t"
            << node->getFlatList()->toString() << "\n";
        node = node->getNext();
    } while(node != apartmentHead);
    *outputStream << "\n";
}

Writer::~Writer() {
    outputStream->close();
    delete outputStream;
}
