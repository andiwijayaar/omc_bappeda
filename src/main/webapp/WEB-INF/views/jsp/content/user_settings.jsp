<div id="content_menus_users" class="table-users display-menu-active">
	<div class="tables">
		<div class="table-flow row">
			<div class="col-sm-12">
				<div class="widget widget-box">
		            <div class="widget-bar">
		                <form role="form" class="form-horizontal">
		                    <div class="row">
		                        <div id="widgetEngineUser" class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
		                            <div class="input-group input-group-sm col-lg-12 col-md-12 col-sm-12 col-xs-12">
		                                <span class="input-group-addon">Show <span id="startSettingUser">0</span>-<span id="endSettingUser">0</span> of <span id="totalsSettingUser">0</span></span>
		                                <span class="input-group-btn">
		                                    <button type="button" class="btn btn-default btn-block btn-sm" id="btnRefreshSettingUser"><i class="fa fa-refresh"></i></button>
		                                </span>
		                            </div>
		                            <div class="input-group input-group-sm col-lg-12 col-md-12 col-sm-12 col-xs-12">
		                                <span class="input-group-addon">Per Page</span>
		                                <select class="form-control" id="perpageSettingUser">
		                                    <option value="10">10</option>
		                                    <option value="20">20</option>
		                                    <option value="50">50</option>
		                                </select>
		                            </div>
		                            <div class="input-group input-group-sm col-lg-6 col-md-6 col-sm-6">
		                            	<span class="input-group-btn">
		                                    <button type="button" class="btn btn-default" id="btnfirstSettingUser"><i class="fa fa-fast-backward"></i></button>
		                                    <button type="button" class="btn btn-default" id="btnprevSettingUser"><i class="fa fa-backward"></i></button>
		                                </span>
		                                <span class="input-group-addon">Page</span>
		                                <input type="text" class="form-control" id="curpageSettingUser" value="1">
		                                <span class="input-group-addon">of <span id="totalpageSettingUser">1</span></span>
		                                <span class="input-group-btn">
		                                    <button type="button" class="btn btn-default" id="btnnextSettingUser"><i class="fa fa-forward"></i></button>
		                                    <button type="button" class="btn btn-default" id="btnlastSettingUser"><i class="fa fa-fast-forward"></i></button>
		                                </span>
		                            </div>
		                            <div class="input-group input-group-sm col-lg-6 col-md-6 col-sm-6">
		                            	<span class="input-group-addon">Search</span>
		                                <input type="text" class="form-control" id="keywordSettingUser" placeholder="Search">
		                                <span class="input-group-btn">
		                                    <button id="btnSearchSettingUser" type="button" class="btn btn-default" ><i class="fa fa-search"> </i></button>
		                                </span>
		                            </div>
		                        </div>
		                    </div>
		                </form>
		            </div>
		            <div class="widget-content">
		                <div class="table-responsive">
		                    <table class="table">
		                        <thead><tr><th class="username">Username</th><th class="fullname">Nama</th><th class="position">Jabatan</th><th class="departement">Departement</th><th class="email">Email</th><th class="hp-numb">Nomor HP</th><th>Action</th></tr></thead>
		                        <tbody id="item-list-setting-users">
		                        </tbody>
		                    </table>
		                    <div class="over-table-user">
		                    	<div id="overlay-users"><h3>Mohon Tunggu...</h3></div>
		                    </div>
		                </div>
		            </div>
		        </div>
		    </div>
		</div>
	</div>
</div>