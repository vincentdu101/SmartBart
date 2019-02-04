import * as React from "react";
import "./PlannerSection.scss";
import { IStationInfo } from "../../../types/StationTypes";
import { IPlannerSectionState, IPlannerSectionProps } from "../../../types/PlannerTypes";
import PlannerTable from "../../PlannerTable/PlannerTable";
import { StationService } from "../../../services/StationService/StationService";
import { Map } from "../../Maps/Map";
import { ICircleEvent } from "../../../types/MapTypes";
import Select from "react-select";

export default class PlannerSection extends React.Component<{}, IPlannerSectionState> {

    constructor(props: IPlannerSectionProps) {
        super(props);

        this.loadStationsInfo = this.loadStationsInfo.bind(this);
        this.originSelection = this.originSelection.bind(this);
        this.destinationSelection = this.destinationSelection.bind(this);
        this.addStationToMapList = this.addStationToMapList.bind(this);
        this.mapHoveredStation = this.mapHoveredStation.bind(this);
        this.outputMapHoverInfo = this.outputMapHoverInfo.bind(this);
        this.parseStationsIntoSelect = this.parseStationsIntoSelect.bind(this);

        this.state = {
            maps: null,
            stations: [],
            mapStations: [],
            mapSelectedStations: [],
            focusedStations: [],
            origin: "",
            destination: "",
            tooltipStation: undefined,
            tooltipActive: false,
            tooltipTextCallback: StationService.outputBartText
        };
    }

    private parseStationsIntoSelect(stations: IStationInfo[]): {value: string, label: string}[] {
        let parsedStations = {};
        return stations.filter((station) => {
            if (!parsedStations[station.abbr]) {
                parsedStations[station.abbr] = station;
                return station;
            } else {
                return false;
            }
        }).map((station: IStationInfo) => {
            return {value: station.abbr, label: station.name};
        });
    }

    private loadStationsInfo(): void {
        this.setState({stations: []});
        StationService.getStationsInfo().then(data => {
            this.setState({stations: this.parseStationsIntoSelect(data), mapStations: data});
        });
    }

    private mapHoveredStation(position: ICircleEvent): void {
        const station = this.state.mapStations[parseInt(position.target.dataset.index)];
        
        this.setState({
            tooltipStation: station,
            tooltipActive: true,
            tooltipTextCallback: StationService.outputBartText
        });
    }

    private addStationToMapList(station: string): IStationInfo[] {
        let selectedStation = StationService.filterForStation(station, this.state.mapStations);
        let mapSelectedStations = this.state.mapSelectedStations;
        mapSelectedStations.push(selectedStation);
        return mapSelectedStations;
    }

    private originSelection(origin: {value: string, label: string}): void {
        this.setState({origin: origin.value, mapSelectedStations: this.addStationToMapList(origin.value)});
    }

    private destinationSelection(destination: {value: string, label: string}): void {
        this.setState({destination: destination.value, mapSelectedStations: this.addStationToMapList(destination.value)});
    }

    private outputMapHoverInfo(position: ICircleEvent): JSX.Element {
        if (!!position) {
            const station = this.state.mapStations.filter((info: IStationInfo) => {
                return info.abbr === position.target.dataset.abbr;
            })[0];
            return StationService.outputBartText(station);
        } else {
            return (<input type="hidden" />);
        }
    }

    public componentDidMount(): void {
        this.setState({
            tooltipStation: undefined,
            tooltipActive: false,
            tooltipTextCallback: StationService.outputBartText
        });

        this.loadStationsInfo();
    }

    public render(): JSX.Element {
        return (
            <section className="planner-section">
                <div className="row planner-menu-row">
                    <div className="col-xs-6 first-col">
                        <Select     className="planner-chosen-select"
                                    options={this.state.stations}
                                    onChange={this.originSelection} />
                    </div>
                    <div className="col-xs-6">
                        <Select     className="planner-chosen-select"    
                                    options={this.state.stations}
                                    onChange={this.destinationSelection} />
                    </div>
                </div>
                <PlannerTable   origin={this.state.origin} 
                                destination={this.state.destination} />

                <Map    maps={this.state.maps} 
                        stations={this.state.mapSelectedStations}
                        console={true}
                        outputMapHoverInfo={this.outputMapHoverInfo} />                                                     
            </section>
        );
    }

}