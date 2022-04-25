<#import "partials/main.ftlh" as main>

<@main.renderWith scripts=[]>

    <h1 class="my-3">Account information (secured) for ${user.email}</h1>

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

</@main.renderWith>