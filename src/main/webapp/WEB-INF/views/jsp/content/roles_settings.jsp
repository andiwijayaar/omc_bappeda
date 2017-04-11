<div id="content_menus_access" class="table-users display-menu-active">
	<div id="box-departement">
		<form action="">
			<div class="form-department">
				<h4>Informasi Akses</h4>
				<div class="box">
					<div class="form-inline">
						<div class="form-group">
							<label class="lbl-grey" for="departement">Nama Akses</label>
							<input type="text" class="form-control" id="role_name" placeholder="Nama Akses" required autocomplete="off">
						</div>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="btn_addAccess">
				<button type="submit" id="addAccess" class="btn btn-default">Tambah Akses</button>
			</div>
		</form>'
	</div>
	<div class="tables">
		<div class="table-flow row">
			<div class="col-sm-12">
				<div class="widget widget-box">
		            <div class="widget-bar">
		                <form role="form" class="form-horizontal">
		                    <div class="row">
		                        <div id="widgetEngineAccess" class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
		                            <div class="input-group input-group-sm col-lg-12 col-md-12 col-sm-12 col-xs-12">
		                                <span class="input-group-addon">Show <span id="startAccess">0</span>-<span id="endAccess">0</span> of <span id="totalsAccess">0</span></span>
		                                <span class="input-group-btn">
		                                    <button type="button" class="btn btn-default btn-block btn-sm" id="btnRefreshAccess"><i class="fa fa-refresh"></i></button>
		                                </span>
		                            </div>
		                            <div class="input-group input-group-sm col-lg-12 col-md-12 col-sm-12 col-xs-12">
		                                <span class="input-group-addon">Per Page</span>
		                                <select class="form-control" id="perpageAccess">
		                                    <option value="10">10</option>
		                                    <option value="20">20</option>
		                                    <option value="50">50</option>
		                                </select>
		                            </div>
		                            <div class="input-group input-group-sm col-lg-6 col-md-6 col-sm-6">
		                            	<span class="input-group-btn">
		                                    <button type="button" class="btn btn-default" id="btnfirstAccess"><i class="fa fa-fast-backward"></i></button>
		                                    <button type="button" class="btn btn-default" id="btnprevAccess"><i class="fa fa-backward"></i></button>
		                                </span>
		                                <span class="input-group-addon">Page</span>
		                                <input type="text" class="form-control" id="curpageAccess" value="1">
		                                <span class="input-group-addon">of <span id="totalpageAccess">1</span></span>
		                                <span class="input-group-btn">
		                                    <button type="button" class="btn btn-default" id="btnnextAccess"><i class="fa fa-forward"></i></button>
		                                    <button type="button" class="btn btn-default" id="btnlastAccess"><i class="fa fa-fast-forward"></i></button>
		                                </span>
		                            </div>
		                            <div class="input-group input-group-sm col-lg-6 col-md-6 col-sm-6">
		                            	<span class="input-group-addon">Search</span>
		                                <input type="text" class="form-control" id="keywordAccess" placeholder="Search">
		                                <span class="input-group-btn">
		                                    <button id="btnSearchAccess" type="button" class="btn btn-default" ><i class="fa fa-search"> </i></button>
		                                </span>
		                            </div>
		                        </div>
		                    </div>
		                </form>
		            </div>
		            <div class="widget-content">
		                <div class="table-responsive">
		                    <table class="table">
		                        <thead><tr><th>Kode Akses</th><th>Nama Akses</th><th>Folder</th><th>Action</th></tr></thead>
		                        <tbody id="item-list-setting-access">
		                        </tbody>
		                    </table>
		                    <div class="over-table-access">
		                    	<div id="overlay-access"><h3>Mohon Tunggu...</h3></div>
		                    </div>
		                </div>
		            </div>
		        </div>
		    </div>
		</div>
	</div>
</div>