<div id="content_menus_department" class="table-users display-menu-active">
	<div id="box-departement">
		<form id="addDepartmentForm">
			<div class="form-department">
				<h4>Informasi Departemen</h4>
				<div class="box">
					<div class="form-inline">
						<div class="form-group">
							<label class="lbl-grey" for="departement">Kode Departemen</label>
							<input type="text" class="form-control" id="departement_kode" placeholder="Kode Departemen" required autocomplete="off">
						</div>
					</div>
					<div class="form-inline">
						<div class="form-group">
							<label class="lbl-grey" for="departement">Nama Departemen</label>
							<input type="text" class="form-control" id="departement_name" placeholder="Nama Departemen" required autocomplete="off">
						</div>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="btn_addDepartment">
				<button type="submit" id="addDepartment" class="btn btn-default">Tambah Departemen</button>
			</div>
		</form>
	</div>
	<div class="tables">
		<div class="table-flow row">
			<div class="col-sm-12">
				<div class="widget widget-box">
		            <div class="widget-bar">
		                <form role="form" class="form-horizontal">
		                    <div class="row">
		                        <div id="widgetEngineDept" class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
		                            <div class="input-group input-group-sm col-lg-12 col-md-12 col-sm-12 col-xs-12">
		                                <span class="input-group-addon">Show <span id="startDept">0</span>-<span id="endDept">0</span> of <span id="totalsDept">0</span></span>
		                                <span class="input-group-btn">
		                                    <button type="button" class="btn btn-default btn-block btn-sm" id="btnRefreshDept"><i class="fa fa-refresh"></i></button>
		                                </span>
		                            </div>
		                            <div class="input-group input-group-sm col-lg-12 col-md-12 col-sm-12 col-xs-12">
		                                <span class="input-group-addon">Per Page</span>
		                                <select class="form-control" id="perpageDept">
		                                    <option value="10">10</option>
		                                    <option value="20">20</option>
		                                    <option value="50">50</option>
		                                </select>
		                            </div>
		                            <div class="input-group input-group-sm col-lg-6 col-md-6 col-sm-6">
		                            	<span class="input-group-btn">
		                                    <button type="button" class="btn btn-default" id="btnfirstDept"><i class="fa fa-fast-backward"></i></button>
		                                    <button type="button" class="btn btn-default" id="btnprevDept"><i class="fa fa-backward"></i></button>
		                                </span>
		                                <span class="input-group-addon">Page</span>
		                                <input type="text" class="form-control" id="curpageDept" value="1">
		                                <span class="input-group-addon">of <span id="totalpageDept">1</span></span>
		                                <span class="input-group-btn">
		                                    <button type="button" class="btn btn-default" id="btnnextDept"><i class="fa fa-forward"></i></button>
		                                    <button type="button" class="btn btn-default" id="btnlastDept"><i class="fa fa-fast-forward"></i></button>
		                                </span>
		                            </div>
		                            <div class="input-group input-group-sm col-lg-6 col-md-6 col-sm-6">
		                            	<span class="input-group-addon">Search</span>
		                                <input type="text" class="form-control" id="keywordDept" placeholder="Search">
		                                <span class="input-group-btn">
		                                    <button id="btnSearchDept" type="button" class="btn btn-default" ><i class="fa fa-search"> </i></button>
		                                </span>
		                            </div>
		                        </div>
		                    </div>
		                </form>
		            </div>
		            <div class="widget-content">
		                <div class="table-responsive">
		                    <table class="table">
		                        <thead><tr><th>ID Departemen</th><th>Nama Departement</th><th>Action</th></tr></thead>
		                        <tbody id="item-list-setting-department">
		                        </tbody>
		                    </table>
		                    <div class="over-table-dept">
		                    	<div id="overlay-dept"><h3>Mohon Tunggu...</h3></div>
		                    </div>
		                </div>
		            </div>
		        </div>
		    </div>
		</div>
	</div>
</div>