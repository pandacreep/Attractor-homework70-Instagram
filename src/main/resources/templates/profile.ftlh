<#import "partials/main.ftlh" as main>

<@main.renderWith scripts=[]>

    <h1 class="my-3">Profile</h1>

    <div class="row flex-wrap">
        <div class="col-3">
            <img style="height: 200px; width: 200px" src="${'data:image/png;base64,'+user.avatar}" alt="avatar"/>
        </div>
        <div class="col-3">
            <p>Posts number: ${user.postsNumber}</p>
            <p>Subscriptions number: ${user.subscriptionsNumber}</p>
            <p>Subscribers number: ${user.subscribersNumber}</p>
            <p>Name: ${user.name}</p>
            <p>Description: ${user.description}</p>
            <p>Phone Number: ${user.phoneNumber}</p>
            <p>Gender: ${user.gender}</p>
        </div>
    </div>
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
                        <p>Description: ${post.description}</p>
                    </div>
                </div>
            </div>
        </#list>
    </div>

</@main.renderWith>