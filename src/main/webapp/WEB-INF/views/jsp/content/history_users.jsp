<div id="content_menus_history_users" class="table-users display-menu-active">
	<div class="text-center">
		<h2>History User</h2>
	</div>
	<div class="tables">
		<div class="table-flow row">
			<div class="col-sm-12">
				<div class="widget widget-box">
		            <div class="widget-bar">
		                <form role="form" class="form-horizontal">
		                    <div class="row">
		                        <div id="widgetEngineHistoryUsers" class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
		                            <div class="input-group input-group-sm col-lg-12 col-md-12 col-sm-12 col-xs-12">
		                                <span class="input-group-addon">Show <span id="startHistoryUsers">0</span>-<span id="endHistoryUsers">0</span> of <span id="totalsHistoryUsers">0</span></span>
		                                <span class="input-group-btn">
		                                    <button type="button" class="btn btn-default btn-block btn-sm" id="btnRefreshHistoryUsers"><i class="fa fa-refresh"></i></button>
		                                </span>
		                            </div>
		                            <div class="input-group input-group-sm col-lg-12 col-md-12 col-sm-12 col-xs-12">
		                                <span class="input-group-btn">
		                                    <button type="button" class="btn btn-default" id="btnfirstHistoryUsers"><i class="fa fa-fast-backward"></i></button>
		                                    <button type="button" class="btn btn-default" id="btnprevHistoryUsers"><i class="fa fa-backward"></i></button>
		                                </span>
		                                <span class="input-group-addon">Page</span>
		                                <input type="text" class="form-control" id="curpageHistoryUsers" value="1">
		                                <span class="input-group-addon">of <span id="totalpageHistoryUsers">1</span></span>
		                                <span class="input-group-btn">
		                                    <button type="button" class="btn btn-default" id="btnnextHistoryUsers"><i class="fa fa-forward"></i></button>
		                                    <button type="button" class="btn btn-default" id="btnlastHistoryUsers"><i class="fa fa-fast-forward"></i></button>
		                                </span>
		                            </div>
		                            <div class="input-group input-group-sm col-lg-2 col-md-2 col-sm-2">
		                            	<span class="input-group-addon">Per Page</span>
		                                <select class="form-control" id="perpageHistoryUsers">
		                                    <option value="10">10</option>
		                                    <option value="20">20</option>
		                                    <option value="50">50</option>
		                                </select>
		                            </div><div id="datetimepicker-start-user" class="input-group date input-group-sm col-lg-5 col-md-5 col-sm-5">
		                                <span class="input-group-addon">Dari</span>
		                                <input type="text" id="startDateUser" class="form-control" placeholder="Dari Tanggal">
		                                <span class="input-group-addon">
						                    <span class="glyphicon glyphicon-calendar"></span>
						                </span>
		                            </div>
		                            <div id="datetimepicker-end-user" class="input-group date input-group-sm col-lg-5 col-md-5 col-sm-5">
			                            <span class="input-group-addon">Sampai</span>
		                                <input type="text" id="endDateUser" class="form-control" placeholder="Sampai Tanggal">
		                                <span class="input-group-addon">
						                    <span class="glyphicon glyphicon-calendar"></span>
						                </span>
						            </div>
		                        </div>
		                    </div>
		                </form>
		            </div>
		            <div class="widget-content">
		                <div class="table-responsive">
		                    <table class="table">
		                        <thead><tr><th class="username">Tanggal</th><th class="fullname">Username</th><th class="departement">Aktivitas</th><th>Action</th></tr></thead>
		                        <tbody id="item-list-history-users">
		                        </tbody>
		                    </table>
		                    <div class="over-table-history-users">
		                    	<div id="overlay-history-users"><h3>Mohon Tunggu...</h3></div>
		                    </div>
		                </div>
		            </div>
		        </div>
		    </div>
		</div>
	</div>
</div>