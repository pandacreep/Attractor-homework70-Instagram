<#import "partials/main.ftlh" as main>

<@main.renderWith scripts=[]>

    <h1 class="my-3">Post</h1>


    <div class="row flex-wrap">
        <div class="col-3">
            <img src="${'data:image/png;base64,'+post.imageString}" class="card-img-top" alt="item picture"/>
        </div>
        <div class="col-3">
            <p>Description: ${post.description}</p>
            <p>Date: ${post.date}</p>
            <form action="/comments/add" method="post">
                <#if _csrf??>
                    <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                </#if>
                <div class="mt-3">
                    <div class="col-2 mx-1">
                        <label>
                            <span class="label-input-text mb-1">Comment: </span>
                            <input type="text" name="comment">
                            <input type="text" name="postId" value="${post.id}" hidden>
                        </label>
                    </div>
                    <br>
                    <div>
                        <button class="btn btn-primary" type="submit">Add</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <#list comments as comment>
        <p>${comment.email} ${comment.dateTime}</p>
        <p>${comment.text}</p>
        <hr>
    </#list>

</@main.renderWith>