package com.github.dnsmoly.leetcode

fun main() {
    // numCourses = 3, prerequisites = [[1,2],[1,0],[2,0]], queries = [[1,0],[1,2]]
    val numCourses = 3
    val prerequisites = arrayOf(intArrayOf(1, 2), intArrayOf(1, 0), intArrayOf(2, 0))
    val queries = arrayOf(intArrayOf(1, 0), intArrayOf(1, 2))
    println(checkIfPrerequisite(numCourses, prerequisites, queries))
}

fun checkIfPrerequisite(numCourses: Int, prerequisites: Array<IntArray>, queries: Array<IntArray>): List<Boolean> {
    val isReachable: Array<Array<Boolean>> = Array(numCourses) { Array(numCourses) { false } }

    for (pre in prerequisites) {
        isReachable[pre[0]][pre[1]] = true
    }

    val result = mutableListOf<Boolean>()
    for (query in queries) {
        val visited = BooleanArray(numCourses)
        val res = isPrerequisite(isReachable, visited, query[0], query[1])
        println("Query: ${query[0]} -> ${query[1]}, reachable: $res")
        result.add(res)
    }
    return result.toList()

}

private fun isPrerequisite(adjList: Array<Array<Boolean>>, visited: BooleanArray, src: Int, target: Int): Boolean {
    visited[src] = true

    if (src == target) {
        return true
    }

    var answer = false
    for (i in 0..adjList[src].size) {
        if (!visited[i]) {
            answer = answer || isPrerequisite(adjList, visited, i, target)
        }
    }
    return answer
}