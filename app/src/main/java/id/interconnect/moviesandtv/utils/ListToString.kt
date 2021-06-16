package id.interconnect.moviesandtv.utils

import id.interconnect.moviesandtv.data.Genre
import id.interconnect.moviesandtv.data.Producer
import id.interconnect.moviesandtv.data.ProductionCompanies

object ListToString {
    fun ProductionCompaniesToString(production_companies: List<ProductionCompanies>): String {
        var production_companies_string = "-"
        if (production_companies.isNotEmpty()) {
            val companyList = ArrayList<String>()
            for (company in production_companies) {
                companyList.add(company.name)
            }
            production_companies_string = companyList.joinToString(
                separator = ", "
            )
        }
        return production_companies_string
    }

    fun GenresToString(genres: List<Genre>): String {
        val genreList = ArrayList<String>()
        for (genre in genres) {
            genreList.add(genre.name)
        }
        val genres_string = genreList.joinToString(separator = ", ")
        return genres_string
    }

    fun createdByListToString(createdBy: List<Producer>): String {
        var createdByString = "-"
        if (createdBy.isNotEmpty()) {
            val createdByList = ArrayList<String>()
            for (producer in createdBy) {
                createdByList.add(producer.name)
            }
            createdByString = createdByList.joinToString(
                separator = ", "
            )
        }
        return createdByString
    }
}