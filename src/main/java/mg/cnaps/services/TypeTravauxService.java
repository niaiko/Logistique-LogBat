package mg.cnaps.services;


import mg.cnaps.models.TypeTravauxMod;


public interface TypeTravauxService extends CRUDService<TypeTravauxMod> {
	TypeTravauxMod getdonneetypetravaux(int idtravaux);
}
