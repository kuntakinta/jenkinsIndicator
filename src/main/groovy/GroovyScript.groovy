import hudson.model.Hudson
import hudson.model.Job
import hudson.model.Result
import hudson.AbortException


def success=true
//list of all jobs taken into consideration in certain job
def jobNames = build.buildVariableResolver.resolve("JOBS").split(',')

for( String jobName : jobNames ){
    println("Checking job: " + jobName)
    Job job = Hudson.instance.getJob(value)

    if (job.lastCompletedBuild.result != Result.SUCCESS){
        println("job " + value+ " was not successful, link to job result page: " + job.lastCompletedBuild.getAbsoluteUrl())
        success=false
    }
}

if (success != true ) {
    throw new AbortException("ERROR - FAILURE")
}
else{
    println "SUCCESS"
}
