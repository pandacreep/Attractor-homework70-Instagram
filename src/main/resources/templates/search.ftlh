<#import "partials/main.ftlh" as main>

<@main.renderWith scripts=[]>

    <h1 class="my-3">Search users</h1>
    <form action="/search" method="post">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
        </#if>
        <div class="row mt-3">
            <div class="col-2 mx-1">
                <label class="flex flex-v-center">
                    <p class="label-input-text mb-1">Email (partial):</p>
                    <input type="text" name="email">
                </label>
            </div>
            <div class="col-2 mx-1">
                <label class="flex flex-v-center">
                    <p class="label-input-text mb-1">Name: (partial)</p>
                    <input type="text" name="name">
                </label>
            </div>
            <div class="col-2 mx-1">
                <label class="flex flex-v-center">
                    <p class="label-input-text mb-1">Description: (partial)</p>
                    <input type="text" name="description">
                </label>
            </div>
            <div class="col-2 mx-1">
                <label class="flex flex-v-center">
                    <p class="label-input-text mb-1">Phone number: (partial)</p>
                    <input type="text" name="phoneNumber">
                </label>
            </div>
            <div class="col-1 pt-3">
                <button class="btn btn-primary" type="submit">Search</button>
            </div>
        </div>
        <br>
        <div class="row flex-wrap" id="searchResult">
            <table>

                <#list users as user>
                    <tr>
                    <td>
                        <a href="/profile/${user.email}">${user.email}</a>
                    </td>
                    <td>
                        <#if user.subscriptionAvailable == true>
                            <a href="/subscribe/${accountEmail}/${user.email}">Subscribe</a>
                        <#else>
                            subscription is not available
                        </#if>
                    </td>
                    </tr>
                </#list>

            </table>
        </div>
        <br>
    </form>

</@main.renderWith>