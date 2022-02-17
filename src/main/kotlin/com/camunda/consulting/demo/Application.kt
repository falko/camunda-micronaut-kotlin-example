package com.camunda.consulting.demo

import io.micronaut.runtime.Micronaut.*
fun main(args: Array<String>) {
	build()
	    .args(*args)
		.packages("com.camunda.consulting.demo")
		.start()
}

