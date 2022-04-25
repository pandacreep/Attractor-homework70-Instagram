<#import "partials/main.ftlh" as main>

<@main.renderWith scripts=[]>

    <h1 class="my-3 text-center">Instagram</h1>
    <div class="row flex-wrap">
        <#list posts as post>
            <div class="col-3">
            <div class="mt-2 cols-2 card">
                <div class="flex flex-col align-center">
                    <#if post.imageString??>
                        <img src="${'data:image/png;base64,'+post.imageString}" class="card-img-top" alt="item picture"/>
                    </#if>
                </div>
                <div class="card-body">
                <span id="-like">
                    <#if post.liked == true>
                        <span class="h1 mx-2 text-danger" id="-likedYes">
                        <i class="fas fa-heart"></i>
                        </span>
                    <#else>
                        <span class="h1 mx-2 muted" id="-likedNot">
                            <a class="as-link" href="/like/${post.id}">
                                <i id="like" class="far fa-heart"></i>
                            </a>
                        </span>
                    </#if>
                    <span class="h1 mx-2 muted" id="-comment-icon">
                        <a class="as-link" href="/post/${post.id}">
                            <i class="far fa-comment"></i>
                        </a>
                    </span>
                </span>

                <p>Description: ${post.description}</p>
                <p>Date: ${post.date}</p>
                </div>
            </div>
            </div>
        </#list>
    </div>

</@main.renderWith>