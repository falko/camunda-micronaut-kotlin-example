package com.camunda.consulting.demo

import info.novatec.micronaut.zeebe.client.feature.ZeebeWorker
import io.camunda.zeebe.client.api.response.ActivatedJob
import io.camunda.zeebe.client.api.worker.JobClient
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.uri.UriBuilder
import jakarta.inject.Singleton
import java.net.URI

@Singleton
class HttpWorker(@param:Client() private val httpClient: HttpClient) {

    @ZeebeWorker(type = "http")
    fun handleHttpJob(client: JobClient , job: ActivatedJob) {
        val uri: URI = UriBuilder.of(job.customHeaders["url"]).build()
        val req: HttpRequest<*> = HttpRequest.GET<Any>(uri)
        httpClient.retrieve(req)
        // TODO extract body and put it into a variable
        client.newCompleteCommand(job).send()
    }

}