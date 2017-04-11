<div id="content_menus_home" class="table-users">
	<div class="text-center">
		<h2>Daftar Permintaan User</h2>
	</div>
	<div class="tables">
		<div class="table-flow row">
			<div class="col-sm-12">
				<div class="widget widget-box">
		            <div class="widget-bar">
		                <form role="form" class="form-horizontal">
		                    <div class="row">
		                        <div id="widgetEngineHome" class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
		                            <div class="input-group input-group-sm col-lg-12 col-md-12 col-sm-12 col-xs-12">
		                                <span class="input-group-addon">Show <span id="start">1</span>-<span id="end">0</span> of <span id="totals">0</span></span>
		                                <span class="input-group-btn">
		                                    <button type="button" class="btn btn-default btn-block btn-sm" id="btnRefreshHome"><i class="fa fa-refresh"></i></button>
		                                </span>
		                            </div>
		                            <div class="input-group input-group-sm col-lg-12 col-md-12 col-sm-12 col-xs-12">
		                                <span class="input-group-addon">Per Page</span>
		                                <select class="form-control" id="perpageHome">
		                                    <option value="10">10</option>
		                                    <option value="20">20</option>
		                                    <option value="50">50</option>
		                                </select>
		                            </div>
		                            <div class="input-group input-group-sm col-lg-6 col-md-6 col-sm-6">
		                            	<span class="input-group-btn">
		                                    <button type="button" class="btn btn-default" id="btnfirstHome"><i class="fa fa-fast-backward"></i></button>
		                                    <button type="button" class="btn btn-default" id="btnprevHome"><i class="fa fa-backward"></i></button>
		                                </span>
		                                <span class="input-group-addon">Page</span>
		                                <input type="text" class="form-control" id="curpageHome" value="1">
		                                <span class="input-group-addon">of <span id="totalpageHome">1</span></span>
		                                <span class="input-group-btn">
		                                    <button type="button" class="btn btn-default" id="btnnextHome"><i class="fa fa-forward"></i></button>
		                                    <button type="button" class="btn btn-default" id="btnlastHome"><i class="fa fa-fast-forward"></i></button>
		                                </span>
		                            </div>
		                            <div class="input-group input-group-sm col-lg-6 col-md-6 col-sm-6">
		                            	<span class="input-group-addon">Search</span>
		                                <input type="text" class="form-control" id="keywordHome" placeholder="Search">
		                                <span class="input-group-btn">
		                                    <button id="btnSearchHome" type="button" class="btn btn-default" ><i class="fa fa-search"> </i></button>
		                                </span>
		                            </div>
		                        </div>
		                    </div>
		                </form>
		            </div>
		            <div class="widget-content">
		                <div class="table-responsive">
		                    <table class="table">
		                        <thead><tr><th class="username">ID Permintaan</th><th class="fullname">Nama Peminta</th><th class="fullname">Tanggal Permintaan</th><th class="email">Email</th><th class="departement">Departemen</th><th class="position">Jabatan</th><th>Action</th></tr></thead>
		                        <tbody id="item-list">
		                        </tbody>
		                    </table>
		                    <div class="over-table">
		                    	<div id="overlay-home"><h3>Mohon Tunggu...</h3></div>
		                    </div>
		                </div>
		            </div>
		        </div>
		    </div>
		</div>
	</div>
</div>