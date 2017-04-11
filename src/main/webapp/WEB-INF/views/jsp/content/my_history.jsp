<div id="content_menus_history_admin" class="table-users display-menu-active">
	<div class="text-center">
		<h2>History Saya</h2>
	</div>
	<div class="tables">
		<div class="table-flow row">
			<div class="col-sm-12">
				<div class="widget widget-box">
		            <div class="widget-bar">
		                <form role="form" class="form-horizontal">
		                    <div class="row">
		                        <div id="widgetEngineHistoryAdmin" class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
		                            <div class="input-group input-group-sm col-lg-12 col-md-12 col-sm-12 col-xs-12">
		                                <span class="input-group-addon">Show <span id="startHistoryAdmin">0</span>-<span id="endHistoryAdmin">0</span> of <span id="totalsHistoryAdmin">0</span></span>
		                                <span class="input-group-btn">
		                                    <button type="button" class="btn btn-default btn-block btn-sm" id="btnRefreshHistoryAdmin"><i class="fa fa-refresh"></i></button>
		                                </span>
		                            </div>
		                            <div class="input-group input-group-sm col-lg-12 col-md-12 col-sm-12 col-xs-12">
		                                <span class="input-group-btn">
		                                    <button type="button" class="btn btn-default" id="btnfirstHistoryAdmin"><i class="fa fa-fast-backward"></i></button>
		                                    <button type="button" class="btn btn-default" id="btnprevHistoryAdmin"><i class="fa fa-backward"></i></button>
		                                </span>
		                                <span class="input-group-addon">Page</span>
		                                <input type="text" class="form-control" id="curpageHistoryAdmin" value="1">
		                                <span class="input-group-addon">of <span id="totalpageHistoryAdmin">1</span></span>
		                                <span class="input-group-btn">
		                                    <button type="button" class="btn btn-default" id="btnnextHistoryAdmin"><i class="fa fa-forward"></i></button>
		                                    <button type="button" class="btn btn-default" id="btnlastHistoryAdmin"><i class="fa fa-fast-forward"></i></button>
		                                </span>
		                            </div>
		                            <div class="input-group input-group-sm col-lg-2 col-md-2 col-sm-2">
		                            	<span class="input-group-addon">Per Page</span>
		                                <select class="form-control" id="perpageHistoryAdmin">
		                                    <option value="10">10</option>
		                                    <option value="20">20</option>
		                                    <option value="50">50</option>
		                                </select>
		                            </div>
		                            <div id="datetimepicker-start" class="input-group date input-group-sm col-lg-5 col-md-5 col-sm-5">
		                                <span class="input-group-addon">Dari</span>
		                                <input type="text" id="startDate" class="form-control" placeholder="Dari Tanggal">
		                                <span class="input-group-addon">
						                    <span class="glyphicon glyphicon-calendar"></span>
						                </span>
		                            </div>
		                            <div id="datetimepicker-end" class="input-group date input-group-sm col-lg-5 col-md-5 col-sm-5">
			                            <span class="input-group-addon">Sampai</span>
		                                <input type="text" id="endDate" class="form-control" placeholder="Sampai Tanggal">
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
		                        <thead><tr><th class="username">Tanggal</th><th class="departement">Aktivitas</th><th>Action</th></tr></thead>
		                        <tbody id="item-list-history-admin">
		                        </tbody>
		                    </table>
		                    <div class="over-table-history-admin">
		                    	<div id="overlay-history-admin"><h3>Mohon Tunggu...</h3></div>
		                    </div>
		                </div>
		            </div>
		        </div>
		    </div>
		</div>
	</div>
</div>